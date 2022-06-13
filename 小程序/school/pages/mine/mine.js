// pages/article/article.js
const API = require('../../utils/api')
const { user, token } = require('../../utils/auth')
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: "",
    encryptedData: "",
    iv: "",
    tmplIds:[]
  },
  login(){
    wx.getUserProfile({
      desc: '必须授权才能使用',
      success:res=>{
      let user=res.userInfo
      wx.setStorageSync('user', user)
      this.setData({
      user:user,
      encryptedData: res.encryptedData,
        iv: res.iv
      })
      var encryptedData=res.encryptedData;
      var iv=res.iv;
      wx.login({
        success:(res)=>{
          if(res.code){
            wx.request({
              url: '',
              method:'POST',
              header:{
                'content-type':'application/x-www-form-urlencoded',
              },
              data: {
                code: res.code,
                encryptedData:encryptedData,
                iv:iv,
              },
              success(res){
              wx.setStorageSync('token', res.data.data)
              }
            })
          }
        }
      })
      },
      fall:res=>{
        console.log('失败',res)
      }
    })
    
  },
  // login(){
  //   let openid=""
  //   //获取用户信息
  //   wx.getUserProfile({
  //     desc: '必须授权才能使用',
  //     success:res=>{
  //     let user=res.userInfo
  //     wx.setStorageSync('user', user)
  //     // console.log('成功',res)
  //     this.setData({
  //     userInfo:user,
  //     encryptedData: res.encryptedData,
  //       iv: res.iv
  //     })
  //     wx.request({
  //       url: 'http://127.0.0.1:8080/wx1/userinfo',
  //       data:{
  //         "encryptedData":res.encryptedData,
  //         "iv":res.iv
  //       }
  //     })
  //     },
  //     fall:res=>{
  //       console.log('失败',res)
  //     }
  //   })
  //   //获取session
  //   const that = this;
  //   wx.login({

  //     success : async (res)  =>{
  //       if (res.code) {
  //         //发起网络请求
  //         wx.request({
  //           url: 'http://127.0.0.1:8080/wx1/login',
  //           method:'POST',
  //           header:{
  //             'content-type':'application/x-www-form-urlencoded',
  //           },
  //           data: {
  //             code: res.code,
  //           },
  //           success(res){
  //             user.openid=res.data.openid
  //             // var token=res.data.token
  //             // wx.setStorageSync('accessToken', res.data.token)
  //             // console.log(token)
  //             if(res.data.status==200){
  //               console.log("恭喜你完成登录")
  //             }else{
  //               wx.removeStorage({
  //                 key: 'LOGIN',
  //               })
  //             }
  //           }
  //         })
  //       } else {
  //         console.log('登录失败！' + res.errMsg)
  //       }
  //     }
  //   })
  // },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var _this = this
    wx.checkSession({
      success: (res) => {
        // 判断是否有token,有token 说明通过了用户验证
        var token = wx.getStorageSync('token')
        if(token===null || token===undefined || token ==='') {
            // 不含有token
        } 
      },
      fail: (res) => {
        console.log("未登陆")
      },
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
    let userInfo = this.data.user
    if (!user) {
      userInfo = '';
    }
    this.setData({
      userInfo: user ,
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

  },
  getProfile: function (e) {
    console.log(e);
    wx.showLoading({
      title: '正在登录...',
    })
    API.getProfile().then(res => {
      console.log(res)
      this.setData({
        userInfo: res
      })
      wx.hideLoading()
    })
    .catch(err => {
      console.log(err)
      wx.hideLoading()
    })
  },
  subscribeMessage: function(template,status) {
    let args = {}
    args.openid = this.data.user.openId
    args.template = template
    args.status = status
    args.pages = getCurrentPages()[0].route
    args.platform = wx.getSystemInfoSync().platform
    args.program = 'WeChat'
    API.subscribeMessage(args).then(res => {
      console.log(res)
      wx.showToast({
        title: res.message,
        icon: 'success',
        duration: 1000
      })
    })
    .catch(err => {
      console.log(err)
      wx.showToast({
        title: err.message,
        icon: 'success',
        duration: 1000
      })
    })
  },

  bindHandler: function(e) {
    let url=e.currentTarget.dataset.url;
    wx.navigateTo({
      url: 'url',
    })
  },

  bindSubscribe: function() {
    wx.requestSubscribeMessage({
      tmplIds: ['dHxDyWWxBnRxKoFdW8SelOfSSbnrCvnjvUTtMF4Tij4'], //这里填入我们生成的模板id
      success(res) {
        wx.request({
          url: '',
          header: {
            "authorization": wx.getStorageSync('token')
          },
          success(res){
            wx.request({
              url: '',
              header: {
                "authorization": wx.getStorageSync('token')
              },
              success(res){
              if(res.data.errorcode=0){
                console.log("订阅成功")
              }else{
                console.log("订阅失败")
              }
              }
            })
          }
        })
      },
      fail(res) {
      }
    })
  },


  setting:function(){
    wx.navigateTo({
      url: '/pages/mine/setting/setting',
    })
  },
  fabu:function(){
    wx.navigateTo({
      url: '/pages/mine/fabu/fabu',
    })
  },
  comment:function(){
    wx.navigateTo({
      url: '/pages/mine/comment/comment',
    })
  },
  shouchang:function(){
    wx.navigateTo({
      url: '/pages/mine/shouchang/shouchang',
    })
  }


})