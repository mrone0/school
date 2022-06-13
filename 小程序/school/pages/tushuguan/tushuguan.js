// pages/tushuguan/tushuguan.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    LibraryList: []

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if(!wx.getStorageSync('token')){
      wx.showToast({
        icon: "error",
        duration: 1500,
        title: '学习要登录',
      })
    }else
    {
      // this.getLibraryList()
    }
  },
  // getLibraryList(){
  //   wx.request({
  //     url: 'https://mrone.vip/wx/library',
  //     header: {
  //       "authorization": wx.getStorageSync("token")
  //     },
  //     method: 'GET',
  //     success: (res) =>{
  //       this.setData({
  //         swiperList: res.data
  //       })
  //     }
  //   })
  // },
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