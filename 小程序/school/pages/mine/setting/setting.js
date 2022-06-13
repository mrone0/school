// pages/index/setting/setting.js
 
const util = require('../../../utils/util.js')
const app = getApp()
const { currentDate } = require('../../../utils/auth')

 
Page({
 
 
 
  /**
   * 页面的初始数据
   */
 
  data: {
 
    info:["头像","名称","收货地址","当前版本","关于"],
 
    tx:"",
 
    name:"",

    currentDate:util.formatTime(new Date(), '-', false),
 
    phone:"",

    addressInfo:[]

  },
 
  bindDateChange: function (e) {
    if(!wx.getStorageSync('token')){
      wx.showToast({
        icon: "error",
        duration: 1500,
        title: '你不是用户哦',
      })
    }else
    {
      var that=this
      success:res=>{ 
        that.setData({
          currentDate:e.detail.valu
        })
        wx.setStorageSync("currentDate",currentDate)
      }
    }

   },
    chooseAddress() {
      var that=this;
      wx.chooseAddress({
        success: (res) => {
          this.setData({
            addressInfo: res
          })
          wx.request({
            url: '',
            method:'POST',
            header: {
              'Content-Type': 'application/x-www-form-urlencoded',
              "authorization": wx.getStorageSync("token"),
            },
            data: {
                username:res.userName,
                phone:res.telNumber,
                address:res.provinceName+res.cityName+res.countyName+res.detailInfo,
                birthday:that.data.currentDate,
            },
            success(res){
              if(res.data.code==200){
                wx.showToast({
                  title: '收到啦！',
                })
                console.log("收到啦")
              }else{
                wx.showToast({
                  title: 'okok',
                })
                console.log("okok")
              }
            }
          })
        },
        fail: function(err) {
          console.log(err)
        }
      })
    },

  /**
   * 生命周期函数--监听页面加载
   */
 
  onLoad: function (options) {
    if(!wx.getStorageSync('token')){
      wx.showToast({
        icon: "error",
        duration: 1500,
        title: '请先登录哦',
      })  
    }
 
    var useInfo = wx.getStorageSync('user')
 
    var that = this;
 
    that.setData({
 
    tx:useInfo.avatarUrl,
 
    name:useInfo.nickName,})
 
 
 
  },
 
  about(){
 
    wx.navigateTo({
 
      url: '/pages/about/about',
 
    })
 
  },
 
  houtai(){
 
    wx.navigateTo({
 
      url: '',
 
    })
 
 
 
  },
 
  version(){
 
    wx.showToast({
 
      title: '当前已是最新版本~',
 
    })
 
 
 
  },

  onReady() {
    wx/wx.setNavigationBarTitle({
      title: '设置',
    })
  },
 
  logout:function() {
 
    wx.removeStorage({
 
      key: 'userInfo',
      key: 'token',
 
      success (res) {
 
        wx.showModal({
 
          title: '提示',
 
          content: '真的要退出了吗',
 
          cancelText:'我骗你的',
 
          confirmText:'是的没错',
 
          confirmColor:'#000000',
 
          cancelColor:'#576b95',
 
          success (res) {
 
            if (res.confirm) {
 
              wx.reLaunch({
 
                url: '/pages/index/index',
 
              })
 
            } else if (res.cancel) {
 
              console.log('用户点击取消')
 
            }
 
          }
 
        })
 
        
 
      }
 
    })
 
 
 
  }
 
 
 
})