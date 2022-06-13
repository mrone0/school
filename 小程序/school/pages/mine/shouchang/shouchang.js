// pages/mine/shouchang/shouchang.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    query:[],
    ShouCangList:[],
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
        title: '你不是用户哦',
      })
    }else
    {
      this.getShouCang()
    }
    this.setData({
      query:options
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    wx/wx.setNavigationBarTitle({
      title: '我的收藏',
    })
    },
  getShouCang(cb){
    this.setData({
      isonloading: true
    })
    //展示loading效果
    wx.showLoading({
      title: '正在加载数据',
    })
    let id=this.options.id;
    wx.request({
      url: 'htts://mrone.vip/wx/user/star',
      header: {
        'content-type':'application/x-www-form-urlencoded',
        "authorization": wx.getStorageSync("token")
      },
      data:{
        current:this.data.page
      },
      method:'POST',
      success:(res)=>{
        this.setData({
          ShouCangList:res.data.records
        })       
      },
      complete: () =>{
        //隐藏loading效果
        wx.hideLoading()
        this.setData({isonloading: false})
        // wx.stopPullDownRefresh()
        cb&&cb()
      }
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
      ShouCangList: [],
      total: 0
    })
    this.getShouCang(() => {
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
    this.getShouCang()
  },

  onTapToDetail:function(event){
    //获取当前文章的id
    var postId=event.currentTarget.dataset.id;
    
    wx.navigateTo({
      url: '../../detail/detail?id='+postId,
    })
  },


  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})