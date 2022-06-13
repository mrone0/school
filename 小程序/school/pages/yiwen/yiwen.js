// pages/yiwen/yiwen.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    query:[],
    QuestionList: [],
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
        title: 'why no login',
      })
    }else
    {
      this.getQuestionList()
    }
    this.setData({
      query:options
    })
  },
  getQuestionList(cb){
    wx.showLoading({
      title: '正在加载数据',
    })
    this.setData({
      isonloading: true
    })
    wx.request({
      url: '',
      header: {
        "authorization": wx.getStorageSync("token")
      },
      data:{
        current:this.data.page
      },
      method: 'GET',
      success: (res) =>{
        this.setData({
          QuestionList: res.data.records
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
      title: '疑问其他',
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
      QuestionList: [],
      total: 0
    })
    this.getQuestionList(() => {
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
    this.getQuestionList()
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
      url: '../detail/detail?id='+postId,
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})