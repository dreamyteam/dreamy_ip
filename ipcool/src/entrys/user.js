import Popup from '../components/pop_up.js'
var cropper  = require('../components/cropper.js');
// var Avatar = require('../components/avatar_upload.js');

$(function() {
   /* $("#avatar_mask").on("click",function(){
        var avatar = new Avatar({
            input: '#avatar_input_upload',
            preview: '#avatar_upload',
            confrimBtn: "#avatar_upload_submit"
        })
    })*/

    $('#avatar_mask').on('click', function() {
        var $inputImage = $("#avatar_input_upload");
        var URL = window.URL || window.webkitURL;
        var blobURL;
        if (URL) {
            $inputImage.change(function() {
                var files = this.files;
                var file;
                if (files && files.length) {
                    file = files[0];
                    if (/^image\/\w+$/.test(file.type)) {
                        blobURL = URL.createObjectURL(file);
                        // 弹框
                        var avatar_popup = new Popup('#avatar_popup');
                        avatar_popup.alert();
                        var $avatar = $("#avatar_upload");
                        $avatar.cropper({
                            aspectRatio: 1 / 1,
                            viewMode:3,
                            dragModel:'move',
                            highlight:false,
                            background: false,
                            crop: function(e) {
                                // console.log(e.x);
                                // console.log(e.y);
                                // console.log(e.width);
                                // console.log(e.height);
                                // console.log(e.scaleX);
                                // console.log(e.scaleY);
                            }
                        })
                        $avatar.one('built.cropper', function() {
                            URL.revokeObjectURL(blobURL);
                        }).cropper('reset').cropper('replace', blobURL);

                        //发送ajax
                        $("#avatar_upload_submit").off("click");
                        $("#avatar_upload_submit").on("click", function() {
                            avatar_popup.destory();
                            var fd = new FormData();
                            fd.append("file", file);
                            $.ajax({
                                url: '/upload/img',
                                type: 'POST',
                                processData: false,
                                contentType: false,
                                data: fd,
                                success: function(result) {
                                    console.log(result);
                                    if (result.error_code == 0) {
                                        var image_url = result.data.image_url;
                                        //赋值hidden input
                                        $("#avatar_image").attr("src", image_url);
                                        $("#hidden_avatar").val(image_url);
                                    } else if (result.error_code > 0) {
                                        console.log(result.error_msg)
                                    }
                                }
                            })
                            return false;
                        })
                    } else {
                        window.alert('请选择图片文件');
                    }
                }
            });
        }
    })
})
