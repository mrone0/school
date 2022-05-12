// pages/map/map.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    subKey: 'ZMHBZ-SSI6X-FXN4O-TB3A2-UJM52-YBBLF',
    enable3d: false,
    showLocation: true,
    showCompass: false,
    enableOverlooking: false,
    enableZoom: true,
    enableScroll: true,
    enableRotate: false,
    drawPolygon: false,
    enableSatellite: false,
    enableTraffic: false,
    latitude: '30.873496',
    longitude: '120.131063',
    markers: [
      {
        'id': 1,
        'name': '仁爱大楼',
        'latitude': '30.726549',
        'longitude': '103.949808',
        'iconPath': '../../images/location.png',
        'width': 32,
        'height': 32
      },
      {
        'id': 2,
        'name': '一食堂',
        'latitude': '30.725541',
        'longitude': '103.951225',
        'iconPath': '../../images/location.png',
        'width': 32,
        'height': 32
      },
      {
        'id': 3,
        'name': '图书馆',
        'latitude': '30.727491',
        'longitude': '103.947920',
        'iconPath': '../../images/location.png',
        'width': 32,
        'height': 32
      },
      {
        'id': 4,
        'name': '运动场',
        'latitude': '30.724867',
        'longitude': '103.953070',
        'iconPath': '../../images/location.png',
        'width': 32,
        'height': 32
      },
      {
        'id': 5,
        'name': '爱心湖',
        'latitude': '30.724987',
        'longitude': '103.949449',
        'iconPath': '../../images/location.png',
        'width': 32,
        'height': 32
      }
    ],
    circles: [],
    polylines: [],
    polygons: [],
    showDialog: false,
    currentMarker: null,
    features: [
      {"name":"宿舍楼","geometry":{"type":"Point","coordinates":[103.950018,30.722871]}},
      {"name":"食堂","geometry":{"type":"Point","coordinates":[103.951225,30.725541]}},
      {"name":"图书馆","geometry":{"type":"Point","coordinates":[103.947920,30.72749]}}
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx/wx.setNavigationBarTitle({
      title: '地图',
    })
    const map = wx.createMapContext("map");
    map.moveToLocation();
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    const markers = this.data.features.map((feature,index) => {
      return {
        id: index,
        latitude: feature.geometry.coordinates[1],
        longitude: feature.geometry.coordinates[0],
        properties: {
          name: feature.name,
          code: feature.code
        },
        width: 24,
        height: 24,
        iconPath: '../../images/location.png'
      }
    });
    
    this.setData({
      markers: markers
    });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  handleMarkerTap(e) {
    console.log(e);
    const marker = this.data.markers.find(item => item.id == e.detail.markerId);
    marker && this.setData({
      currentMarker: marker,
      showDialog: true
    });
    //this.data.showDialog = true;
  },

  navi1() {
    const latitude = this.data.currentMarker.latitude;
    const longitude = this.data.currentMarker.longitude;
    wx.openLocation({
      latitude,
      longitude,
      scale: 18
    });
  },

  navi2() {
    let plugin = requirePlugin('routePlan');
    let key = 'ZMHBZ-SSI6X-FXN4O-TB3A2-UJM52-YBBLF';  //使用在腾讯位置服务申请的key
    let referer = 'wx476cab07cfe094df';   //调用插件的app的名称
    let endPoint = JSON.stringify({  //终点
        'name': this.data.currentMarker.properties['name'],
        'latitude': this.data.currentMarker.latitude,
        'longitude': this.data.currentMarker.longitude
    });
    wx.navigateTo({
        url: 'plugin://routePlan/index?key=' + key + '&referer=' + referer + '&endPoint=' + endPoint
    });
  }
})