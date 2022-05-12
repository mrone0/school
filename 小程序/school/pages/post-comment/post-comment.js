// pages/post/post-comment/post-comment.js
const { user } = require('../../utils/auth')

Page({
  /**
   * 页面的初始数据
   */
  data: {
    useKeyboardFlag:true,
    keyboardInputValue:'',
    //是否显示图片选择面板
    sendMoreMsgFlag:false,
    //保存评论已选择的图片
    chooseFiles:[],
    deleteIndex: -1,
    CommentList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    //接收由post-detail.js页面传递过来的postId
     var postId=options.id;
     this.dbPost=new DBPost(postId);
     var comments=this.dbPost.getCommentData();
    //  console.log(comments);
    this.getCommentList()
     //绑定评论数据
     this.setData({
       comments:comments
     });

  },
  getCommentList(){
    let aid=this.options.id
    wx.request({
      url: 'http://127.0.0.1:8080/comment/detail',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method:'GET',
      data:{
        aid:aid
      },
      success(res){
        this.setData({
          CommentList:res.data
        })
      }
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  // 预览评论图片
  previewImg:function(event){
    //先获取评论序号
    var commentIdx=event.currentTarget.dataset.commentIdx,
    //获取图片在图片数组中的序号
    imgs=this.data.comments[commentIdx].content.img;
   
   //调用微信的图片预览插件
    wx.previewImage({
      urls: imgs, //需要预览的图片url
      current: imgs[commentIdx]  //当前展示的图片的url
    })

  },

  //切换语音和文本输入
  switchInputType:function(){
    this.setData({
      useKeyboardFlag: !this.data.useKeyboardFlag
    });
  },

  //input触发事件
  bindCommentInput:function(event){
     var val=event.detail.value;
    //  console.log(val);
     //屏蔽关键字
    //  var pos=event.detail.cursor;
    //  if(pos!=-1){
    //    //光标在中间
    //    var left=event.detail.value.slice(0,pos);
    //    console.log(left);
    //    //计算光标的位置
    //    pos=left.replace(/qq/g,'*').length;
    //  }
    //  return {
    //    value:val.replace(/qq/g, '*'),
    //   cursor:pos
    //  }
     this.data.keyboardInputValue=val;
    },

    //发送按钮事件
  submitComment:function(event){
    var imgs = this.data.chooseFiles;
    var newData={
      username:"官官",
      avatar:"/images/post/avatar-3.png",
      //评论时间
     create_time:new Date().getTime()/1000,
     content: {
       txt: this.data.keyboardInputValue,
       img: imgs,
       audio: null
     },
    };
    if (!newData.content.txt&&imgs.length===0) {
       //如果没有评论内容，就不执行任何操作
       return;
    }

    //1、如果有评论内容，就保存到缓存中
    this.dbPost.newComment(newData);
    //2、显示评论发表成功提示
    this.showCommitSucessToast();
    //3、重新渲染并绑定所有评论，将当前发表的评论显示到列表中
    this.bindCommentData();
    //4、恢复初始状态
    this.resetAllDefaultStatus();
  },

  //2\评论成功提示
  showCommitSucessToast:function(){
    wx.showToast({
      title: '评论成功',
      duration:1000,
      icon:"success"
    })
  },

  //3\把新评论增加到评论列表中显示
  bindCommentData:function(){
    var comments=this.dbPost.getCommentData();
    //绑定评论数据
    this.setData({
      comments: comments
    });
  },

  //4、恢复初始状态
  resetAllDefaultStatus:function(){
    this.setData({
      keyboardInputValue: '',
      chooseFiles:[],
      sendMoreMsgFlag:false
    });
  },

  //评论添加图片按钮事件
  sendMoreMsg:function(){
    this.setData({
      sendMoreMsgFlag: !this.data.sendMoreMsgFlag
    });
   
  },

  //上传评论照片
  chooseImage:function(event){
     var imgArr=this.data.chooseFiles;
     //只能上传3张照片，包括拍照
     var leftCount=3-imgArr.length;
     if(leftCount<=0){
       return;
     }
     //获取--选择的是哪一张传图方式
     var sourceType=[event.currentTarget.dataset.category],
     that=this;
    //  console.log(sourceType);
     wx.chooseImage({
       count: leftCount,
       sourceType: sourceType,
       success: function(res) {
        //可以分次选择图片，但总数不能超过3张
        that.setData({
          chooseFiles:imgArr.concat(res.tempFilePaths)
        });
       },
     })
    },

    //删除选择的图片
  deleteImage:function(event){
   var index=event.currentTarget.dataset.idx,
   that=this;
   that.setData({
     deleteIndex:index
   });
  //  console.log(index);
   that.data.chooseFiles.splice(index,1);
   //setTimeout() 方法用于在指定的毫秒数后调用函数或计算表达式。
   setTimeout(function(){
     that.setData({
       deleteIndex:-1,
       chooseFiles: that.data.chooseFiles
     });
   },300);
  
  },

  // //语音评论事件
  // recordStart:function(){

  //   var that=this;
  //   this.setData({
  //     recodingClass:'recoding'
  //   });
  //   //记录录音开始时间
  //   this.startTime=new Date();
  //   wx.startRecord({
  //     success:function(res){
  //       //计算录音时长
  //       var diff=(that.endTime-that.startTime)/1000;
  //       diff=Math.ceil(diff);

  //       //发送录音
  //       that.submitVoiceComment({
  //         url:res.temFilePath,
  //         timeLen:diff
  //       });
  //     },
  //     fail:function(res){
  //      console.log(res);
  //     },
  //     complete:function(res){
  //       console.log(res);
  //     }
  //   })
  // },

  // //结束语音事件
  // recordEnd:function(){
  //   this.setData({
  //     recordingClass:''
  //   });
  //   this.endTime=new Date();
  //   wx.stopRecord();
  // },

  // //提交语音事件
  // submitVoiceComment:function(audio){
  //   var newData={
  //     username: "官官",
  //     avatar: "/images/avatar/avatar-3.png",
  //     //评论时间
  //     create_time: new Date().getTime() / 1000,
  //     content: {
  //       txt: '',
  //       img: [],
  //       audio: audio
  //      }
  //   };
  //   //保存新评论到缓存中
  //   this.dbPost.newComment(newData);
  //   //显示发表后的结果
  //   this.showCommitSucessToast();
  //   //重新渲染评论列表
  //   this.bindCommentData();
  // }
})