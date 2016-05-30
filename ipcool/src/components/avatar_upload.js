var cropper = require('./cropper.js');
import Popup from '../components/pop_up.js'


function Avatar(cfg) {
    this.cfg = cfg;
    this.input = null; //输入框
    this.preview = null; //图片采取预览框
    this.confrimBtn = null;
    this.originFileType = null; //原始图片类型
    this.originFileName = null; //原始图片名称
    this.init();
}
Avatar.prototype = {
    init: function() {
        this.input = $(this.cfg.input);
        this.preview = $(this.cfg.preview);
        this.confrimBtn = $(this.cfg.confrimBtn);
        this.inputHandler();
    },
    inputHandler: function() {
        var self = this;
        var URL = window.URL || window.webkitURL;
        this.input.on("change", function() {
            var files = this.files;
            var file;
            var blobURL;
            if (files && files.length) {
                file = files[0];
                if (/^image\/\w+$/.test(file.type)) { // 是图片文件的处理TODO 非图片文件提示
                    self.originFileType = file.type;
                    self.originFileName = file.name;
                    //弹窗
                    var avatar_popup = new Popup("#avatar_popup");
                    avatar_popup.alert();

                    console.log(self.originFileType + self.originFileName);
                    blobURL = URL.createObjectURL(file);
                    //此处正式时候为弹窗
                    self.preview.cropper({
                        aspectRatio: 1 / 1,
                    }).cropper('replace', blobURL)
                    self.confrimBtn.off('click');
                    self.confrimBtn.on("click", function() {
                        self.cropImg();
                    })
                }
            }
        })
    },
    cropImg: function() {
        var size = {
            width: 100,
            height: 100
        };
        var croppedCanvas = this.preview.cropper("getCroppedCanvas", size); // 生成canvas对象
        var croppedCanvasUrl = croppedCanvas.toDataURL("image/png"); //Base64

        console.log(croppedCanvasUrl);
        // var croppedBlob = dataURLtoBlob(croppedCanvasUrl);
        // croppedBlob.name = this.originFileName;
        // var w = window.open('about:blank', 'image from canvas');
        // w.document.write("<img src='" + crop + "' alt='from canvas'/>");

        // this.uploadAjax(croppedBlob);

       /* function dataURLtoBlob(dataurl) { //字符串转二进制
            var arr = dataurl.split(','),
                mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]),
                n = bstr.length,
                u8arr = new Uint8Array(n);
            while (n--) {
                u8arr[n] = bstr.charCodeAt(n);
            }
            return new Blob([u8arr], { type: mime });
        }*/
    },
    uploadAjax: function(croppedBlob) {
        console.log(croppedBlob)
        var fd = new FormData();
        fd.append("file", croppedBlob)
        $.ajax({
            url: '/upload/img',
            type: 'POST',
            processData: false,
            contentType: false,
            data: fd,
            success: function(result) {
                console.log(result);
                if (result.error_code == 0) {
                    // var image_url = result.data.image_url;
                    //赋值hidden input
                    // $("#avatar_image").attr("src", image_url);
                    // $("#hidden_avatar").val(image_url);
                } else if (result.error_code > 0) {
                    console.log(result.error_msg)
                }
            }
        })
    }
};
module.exports = Avatar;
