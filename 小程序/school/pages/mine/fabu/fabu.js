// pages/yiwen/yiwen.js
const { user } = require('../../../utils/auth')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    query:[],
    FaBuList: [],
    total: 0,
    page: 1,
    pagesize: 10,
    isonloading: false

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if(!wx.getStorageSync('token')){
      wx.showToast({
        icon: "error",
        duration: 1500,
        title: '你没有登录',
      })
    }else
    {
      this.getFaBuList()
    }

    this.setData({
      query:options
    })
  },
  getFaBuList(cb){
    wx.showLoading({
      icon:'error',
      duration: 1500,
      title: '正在加载数据',
    })
    this.setData({
      isonloading: true
    })
    wx.request({
      url: 'http://127.0.0.1:8080/wx/user/fabu',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'GET',
      success: (res) =>{
        this.setData({
          FaBuList: res.data
        })
      },
      complete: () =>{
        wx.hideLoading()
        this.setData({isonloading: false})
        cb&& cb()
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    wx/wx.setNavigationBarTitle({
      title: '我的发布',
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

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
    this.setData({
      page: 1,
      FaBuList: [],
      total: 0
    })
    this.getFaBuList(() => {
      wx.stopPullDownRefresh()
    })
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    if(this.data.isonloading)return
    this.setData({
      page: this.data.page +1
    })
    this.getFaBuList()
  },

  // event  事件
  // onGoToDetail(event) {
    // 使用事件对象 拿到我们当前文章的文字号 
    // 通过 currentTarget.dataset 可以拿到我们事件目标上自定义的 data-属性
    // 我们可以通过这个对象拿到定义在事件对象上的属性 data- 后面的就是自定义的属性
    // 小程序自定义的 data- 属性的规则
    // data-id ---> id
    // data-Id ---> id
    // data-postId ---> postid
    // data-PostId ---> postid
    // data-post-id ---> postId
    // data-post-ID ---> postId
  //   let id=e.currentTarget.dataset.postId
  //   console.log(event.currentTarget.dataset);
  //   wx.navigateTo({
  //     // 跳转文章详情页
  //     url: "/pages/detail/detail?pid="+event.currentTarget.dataset.id,
  //   })
  // },

  onTapToDetail:function(event){
    //获取当前文章的id
    var postId=event.currentTarget.dataset.id;
    
    wx.navigateTo({
      url: '../../../pages/detail/detail?id='+postId,
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})