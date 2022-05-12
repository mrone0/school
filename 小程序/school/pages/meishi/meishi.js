// pages/meishi/meishi.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    query:[],
    meishiList:[],
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
        title: '付费解锁懂？',
      })
    }else
    {
      this.getmeishiList()
    }
    this.setData({
      query:options
    })
  },
  //以分页的形式获取商铺列表数据的方法
  getmeishiList(cb){
    this.setData({
      isonloading: true
    })
    //展示loading效果
    wx.showLoading({
      title: '正在加载数据',
    })
    wx.request({
      url: 'http://127.0.0.1:8080/wx/meishi',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'GET',
      data:{
        _page: this.data.page,
        _pagesize: this.data._pagesize
      },
      success: (res) =>{
        this.setData({
          meishiList: [...this.data.meishiList, ...res.data],
          total: res.header['X-Total-Count'] -0
        })
      },
      complete: () =>{
        //隐藏loading效果
        wx.hideLoading()
        this.setData({isonloading: false})
        // wx.stopPullDownRefresh()
        cb&& cb()
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    wx/wx.setNavigationBarTitle({
      title: '美食',
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
      meishiList: [],
      total: 0
    })
    this.getmeishiList(() => {
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
    this.getmeishiList()
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})