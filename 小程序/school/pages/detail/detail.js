// pages/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  count:'',
  articleList:'',
  CommentList:'', 
  time:'',
  isClick:'',
  click:'',
  inputValue:'',
  isShow: false, //控制emoji表情是否显示 
  isLoad: true, //解决初试加载时emoji动画执行一次
  cfBg: false,
  emojiChar: "☺-😋-😌-😍-😏-😜-😝-😞-😔-😪-😭-😁-😂-😃-😅-😆-👿-😒-😓-😔-😏-😖-😘-😚-😒-😡-😢-😣-😤-😢-😨-😳-😵-😷-😸-😻-😼-😽-😾-😿-🙊-🙋-🙏-✈-🚇-🚃-🚌-🍄-🍅-🍆-🍇-🍈-🍉-🍑-🍒-🍓-🐔-🐶-🐷-👦-👧-👱-👩-👰-👨-👲-👳-💃-💄-💅-💆-💇-🌹-💑-💓-💘-🚲",
  //0x1f---
  
  emoji: [
    "01", "02", "03", "04", "05", "06", "07", "08", "09","10", 
    "11", "12", "13", "14", "15", "16", "17", "18", "19","20", 
    "21", "22", "23", "24", "25", "26", "27", "28", "29","30", 
    "31", "32", "33", "34", "35", "36", "37", "38", "39","40", 
    "41", "42", "43", "44", "45", "46", "47", "48", "49","50", 
    "51", "52", "53", "54", "55"
  ],
  emojis: [], //qq、微信原始表情
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad:function (options) {
 // 通过监听页面加载的的函数的参数options，机也可以拿到我们传递过来本页面的参数
    // 注意：查询字符串（参数）的类型都是字符串类型
    // console.log(options);
    var id=options.id;
    this.getArticleList()
    this.getCommentList()
    this.getStar()
    this.getzan()
     // 页面初始化 options为页面跳转所带来的参数
     var em = {}, that = this, emChar = that.data.emojiChar.split("-");
     var emojis = []
     that.data.emoji.forEach(function (v, i) {
       em = {
         char: emChar[i],
         emoji: v
       };
       emojis.push(em)
     });
        that.setData({
         emojis: emojis
       })
  },
  haveSave(e){
    let id=this.options.id
      wx.request({
        url: 'https://mrone.vip/wx/star',
        header: {
          'Content-Type': 'application/x-www-form-urlencoded',
          "authorization": wx.getStorageSync("token")
        },
        data:{id:id},
        method:'POST',
        success:(res)=>{
          if(res.data.code==1){
            this.setData({
              isClick:true
            })
            wx.showToast({
              title: '已收藏',
            })
          }else{
            this.setData({
              isClick:false
            })
            wx.showToast({
              icon:'none',
              title: '已取消收藏',
            })
          }
        }
      })
  },
  havezan(e){
    let id=this.options.id
      wx.request({
        url: 'https://mrone.vip/wx/zan',
        header: {
          'Content-Type': 'application/x-www-form-urlencoded',
          "authorization": wx.getStorageSync("token")
        },
        data:{id:id},
        method:'POST',
        success:(res)=>{
          if(res.data.code==0){
            this.setData({
              click:true,
            })
          }else{
            this.setData({
              click:false,
            })
          }
        }
      })
  },
  
  getArticleList(){
    let id=this.options.id
    wx.request({
      url: 'https://mrone.vip/wx/detail/article',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      data:{id:id},
      method: 'GET',
      success: (res) =>{
        this.setData({
          articleList: res.data
        })
      }
    })
  },
  getCommentList(){
    let id=this.options.id;
    wx.request({
      url: 'https://mrone.vip/comment/detail',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      data:{aid:id},
      method:'GET',
      success:(res)=>{
        this.setData({
          CommentList:res.data
        })
      }
    })
  },
  getStar(){
    let id=this.options.id;
    wx.request({
      url: 'https://mrone.vip/wx/show',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        "authorization": wx.getStorageSync("token")
      },
      data:{id:id},
      method:'POST',
      success:(res)=>{
        if(res.data.code==1){
        this.setData({
          isClick:true
        })
      }else{
        this.setData({
          isClick:false
        })
      }
      }
    })
  },
  getzan(){
    let id=this.options.id;
    wx.request({
      url: 'https://mrone.vip/wx/get/zan',
      header: {
        'Content-Type': 'application/x-www-form-urlencoded',
        "authorization": wx.getStorageSync("token")
      },
      data:{id:id},
      method:'POST',
      success:(res)=>{
        if(res.data.code==1){
        this.setData({
          click:true,
          count:res.data.data
        })
      }else{
        this.setData({
          click:false,
          count:res.data.data
        })
      }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    // 评论弹出层动画创建
    this.animation = wx.createAnimation({
      duration: 400, // 整个动画过程花费的时间，单位为毫秒
      timingFunction: "ease", // 动画的类型
      delay: 0 // 动画延迟参数
    })
  },
  showTalks: function() {
    // 加载数据
    this.loadTalks();
    // 设置动画内容为：使用绝对定位显示区域，高度变为100%
    this.animation.bottom("0rpx").height("100%").step()
    this.setData({
     talksAnimationData: this.animation.export()
    })
    },
    
    hideTalks: function() {
    // 设置动画内容为：使用绝对定位隐藏整个区域，高度变为0
    this.animation.bottom("-100%").height("0rpx").step()
    this.setData({
     talks: [],
     talksAnimationData: this.animation.export()
    })
    },
    
    // 加载数据
    loadTalks: function() {
    // 随机产生一些评论
    },
    
    onScrollLoad: function() {
    // 滚动评论区加载新数据加载新的数据
    this.getCommentList();
    },

//解决滑动穿透问题
emojiScroll: function(e) {
console.log(e)
},

//点击表情显示隐藏表情盒子
emojiShowHide: function() {
this.setData({
 isShow: !this.data.isShow,
 isLoad: false,
 cfBg: !this.data.false
})
},

//表情选择
emojiChoose: function(e) {
//当前输入内容和表情合并
if(!this.data.inputValue){
  this.data.inputValue==''
  this.data.inputValue+=e.currentTarget.dataset.emoji;
}else{
  this.data.inputValue+=e.currentTarget.dataset.emoji;
}
this.setData({
 inputValue: this.data.inputValue
})
},

//点击emoji背景遮罩隐藏emoji盒子
cemojiCfBg: function() {
this.setData({
 isShow: false,
 cfBg: false
})
},

//下拉评论框隐藏
touchStart: function(e) {
let touchStart = e.touches[0].clientY;
this.setData({
 touchStart,
})
},

//输入框失去焦点时触发
bindInputBlur: function(e) {
if(!this.data.inputBiaoqing){
  this.data.inputBiaoqing='',
  this.data.inputValue = e.detail.value + this.data.inputBiaoqing;

}
},
faBu: function() {
  let id=this.options.id;
  wx.request({
    url: 'https://mrone.vip/comment/add',
    method:'POST',
    header: {
    'Content-Type': 'application/x-www-form-urlencoded',
    "authorization": wx.getStorageSync("token"),
    },
    data:{
       comment:this.data.inputValue,
       id:id
    },
    success(res){
      if(res.data.code==0){
        wx.showToast({
          title: '评论成功',
        })
      }else{
        wx.showToast({
          icon:'error',
          title: '评论失败',
        })
      }
    }
  })
},

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})