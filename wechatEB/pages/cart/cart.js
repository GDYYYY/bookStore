const app = getApp()
Page({
  data: {
    books: [],
    price: [],
    target: "",
    isLog: false,
    totalPrice: 0,
    checkedBook: [],
    hackReset: true,
  },
  onShow: function (options) {
    var that = this;
    this.setData({ isLog: app.globalData.isLog });
    this.setData({ u_id: app.globalData.u_id });
    if (app.globalData.isLog) {
      wx.request({
        url: 'http://localhost:8080/getCart?u_id=' + app.globalData.user.u_id,
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
            price: res.data
          })
        }
      });
    }
  },
  onChange(event) {
    const that = this;
    this.setData({
      checkedBook: event.detail,
    });
    wx.request({
      url: 'http://localhost:8080/getCart?u_id=' + app.globalData.user.u_id,
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
        });
        let sum = 0;
        var b;
        that.data.checkedBook.forEach(c => {
          for (let i = 0; i < that.data.books.length; i++) {
            if (that.data.books[i].id == c) {
              b = that.data.books[i];
              break;
            }
          }
          console.log(that.data.price[b.b_id - 1]);
          console.log(b);
          sum += that.data.price[b.b_id - 1].price * b.num;
          console.log(sum);
        });
        that.setData({
          totalPrice: sum.toFixed(4),
        });
      },
    })
  },
  flush() {
    const that = this;
    this.setData({ hackReset: false });
    console.log('czz');
    wx.request({
      url: 'http://localhost:8080/getCart?u_id=' + app.globalData.user.u_id,
      method: "POST",
      data: {
      },
      header: {
        'content-type': 'application/json',
      },
      success(res) {
        that.setData({
          books: res.data,
          totalPrice: 0,
          hackReset: true,
        });
      }
    })
  },
  onSubmit() {
    const that = this;
    wx.request({
      url: 'http://localhost:8080/newForm?u_id=' + app.globalData.user.u_id + '&cost=' + that.data.totalPrice,
      method: "POST",
      data: {
      },
      header: {
        'content-type': 'application/json',
      },
      success(res) {
        console.log(res);
        let f_id = res.data.f_id;
        console.log(f_id);
        that.data.checkedBook.forEach(c => {
          // let element = that.data.books[b - 1];
          var element;
          for (let i = 0; i < that.data.books.length; i++) {
            if (that.data.books[i].id == c) {
              element = that.data.books[i];
              break;
            }
          }
          wx.request({
            url: 'http://localhost:8080/addOrders?f_id=' + f_id + '&u_id=' + app.globalData.user.u_id + '&num=' + element.num + '&b_id=' + element.b_id,
            method: "POST",
            data: {
            },
            header: {
              'content-type': 'application/json',
            },
            success(res) {
            }
          });
          wx.request({
            url: 'http://localhost:8080/delCart?id=' + element.id,
            method: "POST",
            data: {
            },
            header: {
              'content-type': 'application/json',
            },
            success(res) {
              that.flush();
              that.setData({ checkedBook: [] });
            }
          })
        })
        wx.showToast({
          title: '购买成功！',
          icon: 'success',
          duration: 1500
        })
        that.flush();
      }
    })
  }
})