// pages/nongchang/nongchang.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    FarmList: []

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if(!wx.getStorageSync('token')){
      wx.showToast({
        icon: "error",
        duration: 1500,
        title: '登录再看',
      })
    }else
    {
      this.getFarmList()
    }
  },
  getFarmList(){
    wx.request({
      // url: 'http://127.0.0.1:8080/wx/farm',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      method: 'GET',
      success: (res) =>{
        this.setData({
          swiperList: res.data
        })
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