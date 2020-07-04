const app=getApp();
Component({
  properties: {
    b_id:{
      type: String,
      value: "",
    },
    num:{
      type: Number,
      value: "",
    },
    role:{
      type: Number,
      value: "",
    },
  },
  data: {
    url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
    title: "title",
    author: "author",
    price: "price",
    u_id: "",
  },
  ready:function () {
    var that = this;
    console.log(that.properties.b_id);
    wx.request({
      url: 'http://localhost:8080/getBook?b_id='+ that.properties.b_id,
      method: "GET",
      header: {
        'content-type': 'application/json',
      },
      success(resp) {
        console.log(resp);
        that.setData({
          author: resp.data.author+'\n',
          title: resp.data.name+'\n',
          url: resp.data.bookInfo.image,
          price: resp.data.price,
        });
        console.log("sss");
      }
    })
  },
  methods:{
  toDetails: function () {
    console.log(this.properties.b_id);
    app.globalData.b_id=this.properties.b_id;
    wx.navigateTo({
      url: '../details/details',
    })
  },
  noop(){},
  onChange:function(event){
    const that=this;
    console.log(event.detail);
    console.log(that.properties.num);
    this.triggerEvent('reCheck',that.properties.num)
    wx.request({
      url: 'http://localhost:8080/updateCart?u_id='+ app.globalData.user.u_id+'&b_id='+that.properties.b_id+'&num='+event.detail,
      method: "POST",
      header: {
        'content-type': 'application/json',
      },
      success(resp) {
        console.log(resp);
        // that.setData({
        //   book:resp.data
        // });
      }
    })
  }
}
})