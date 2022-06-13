// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //存放轮播图的数据列表
    swiperList:[],
    //存放功能数据列表
    gridList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
      this.getSwiperList(),
      this.getGridList()
  },
  
  //获取轮播图数据的方法
  getSwiperList(){
    wx.request({
      url: '',
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
  
  //获取九宫格数据的方法
  getGridList(){
    wx.request({
      url: '',
      method: 'GET',
      success: (res) =>{
        this.setData({
          gridList: res.data
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