function myajax(data,ajaxUrl) {
    $.ajax({
        type:"POST",
        url:ajaxUrl,
        traditional: true,
        data:data,
        dataType: 'json',
        success:function (data) {
            console.log(data)
        },
        error:function (res) {
            console.log("error")
        },
    });
}

function getGUID() {
    function S4() {
        return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    };
    var UID = S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4();
    var timestamp=new Date().getTime();
    var GUID = UID + timestamp;
    return GUID;
}