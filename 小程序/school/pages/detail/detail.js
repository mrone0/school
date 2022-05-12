// pages/detail/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    articleList:''
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
  },
  getArticleList(){
    let id=this.options.id
    wx.request({
      url: 'http://127.0.0.1:8080/wx/detail/article',
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