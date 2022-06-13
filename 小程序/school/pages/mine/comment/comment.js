// pages/mine/comment/comment.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userCommentList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if(!wx.getStorageSync('token')){
      wx.showToast({
        icon: "error",
        duration: 1500,
        title: '你不是用户哦',
      })
    }else
    {
      this.getUserCommentList()
    }

  },

  getUserCommentList(){
    wx.request({
      url: '',
      method:'POST',
      header: {
        "authorization": wx.getStorageSync("token"),
      },
      method:'POST',
      success: (res) =>{
        this.setData({
          userCommentList: res.data
        })
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

  delete:function(event){
    var aid=event.currentTarget.dataset.aid;
    var id=event.currentTarget.dataset.id
    wx.showModal({
      title: '提示',
      content: '确定删除吗',
      cancelText:'取消',
      confirmText:'删除',
      confirmColor:'#000000',
      cancelColor:'#576b95',
      success (res) {
        if (res.confirm) {
          wx.request({
            url: '',
            method: 'GET',
            header: {
              'Content-Type': 'application/x-www-form-urlencoded',
              "authorization": wx.getStorageSync("token")
            },
            data:{
              aid:aid,
              id:id
            },
            success(res){
              if(res.data.code==0){
                wx.showToast({
                  title: '删除成功',
                })
              }else{
                wx.showToast({
                  icon:'error',
                  title: '删除失败',
                })
              }
          }
        })
        } else if (res.cancel) {
        }
      }
    })
  },
 
  detail:function(event){
        //获取当前文章的id
        var postId=event.currentTarget.dataset.id;
        wx.navigateTo({
          url: '../../../pages/detail/detail?id='+postId,
        })
  }
})

