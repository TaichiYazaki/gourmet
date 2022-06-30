'use strict';
$(function () {
  $(document).on('submit', '.favorite-button', function () {

    $.ajax({
      url: "http://localhost:8080/execute-favorite"
      , type: 'POST'
      , dataType: 'json'
      , data: {
        _csrf: $("*[name=_csrf]").val()  // CSRFトークンを送信
      }
      , async: true
    }).done(function () {
      console.log("通信成功！！")
    }).fail(function () {
      console.log("通信失敗、、、")
    }).always(function () {
      console.log("通信終了")
    })
  })
 window.sessionStorage.setItem(['favorite'],['お気に入り']);
  window.sessionStorage.setItem(['removeFavorite'],['お気に入り解除']);
  var favorite = window.sessionStorage.getItem(['favorite']);
  var removeFavorite = window.sessionStorage.getItem(['removeFavorite']);
    $('.button',this).on('click', function(){
      if($(this).val()=== favorite){
        $(this).val(removeFavorite)
      } else if($(this).val() === removeFavorite){
        $(this).val(favorite)
      }
    })
  });

