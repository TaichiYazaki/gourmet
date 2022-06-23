'use strict';
function change()
{
    let name =  document.getElementById("favorite-button");
    if(name.value=="お気に入り") 
    
    name.value = "お気に入り登録済み";
    
   else name.value= "お気に入り";
}

