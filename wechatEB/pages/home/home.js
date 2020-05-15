const app = getApp()

Page({
  data: {
    books:[],
    target:""
  },
  onLoad: function (options) {
    var that = this;
      wx.request({
        url: 'http://localhost:8080/getBooks',
        method: "POST",
        data: {
        },
        header: {
          'content-type': 'application/json', 
        },
        success(res) {
          console.log(res);
          that.setData({
            books: res.data
          })
        }
      });
  },
  //详情
  toDetails: function (event) {
    console.log(event.currentTarget.dataset.b_id);
    app.globalData.b_id=event.currentTarget.dataset.b_id;
    wx.navigateTo({
      url: '../details/details',
    })
  },
})
