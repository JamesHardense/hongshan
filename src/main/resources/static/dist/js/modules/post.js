/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2022, hongshan. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/

define(function(require, exports, module) {
	J = jQuery;
	require('tagsinput');

	var PostView = function () {};
	
	PostView.prototype = {
        name : 'PostView',
        init : function () {
        	this.bindEvents();
        },
        defaults: {
        	type : 'text',
        	defaultEditor: 'ueditor',
        	maxFiles : 6,
        },
        bindEvents : function () {
        	var that = this;

        	// that.bindTagit();
        	// that.bindValidate();
        	that.bindUpload();

            $('button[event="post_submit"]').click(function () {
                if(document.getElementById("title").value===""){
                    alert("请输入词条名称");
                    document.getElementById("title").focus()
                    return false;
                }
                if(document.getElementById("content").value===""){
                    alert("请输入词条内容");
                    document.getElementById("content").focus()
                    return false;
                }
                if(document.getElementById("channelId").value===""){
                    alert("请输入词条分类");
                    document.getElementById("channelId").focus()
                    return false;
                }
                // var status = $(this).data('status');
                // $("input[name='status']").val(status);
                // $("#submitForm").submit();
                if(document.getElementById("id").valueOf()===""){
                    document.getElementById("id").value=0;
                }
                var  str ={
                    title:document.getElementById("title").value,
                    content:document.getElementById("content").value,
                    channelId:document.getElementById("channelId").value,
                    id:document.getElementById("id").value,
                    authorId:document.getElementById("authorId").value,
                    editor:document.getElementById("editor").value,
                    status:document.getElementById("status").value};
                fetch(`http://localhost:9090/post/submit`,{
                    method:'POST',
                    body:JSON.stringify(str),
                    headers:{'Content-Type':'application/json'}
                }).then((res=>{
                    console.log(res);
                       // if(res.status==200){
                       //     layer.confirm('等待管理员审核', {
                       //         btn: ['确定'], //按钮
                       //         shade: false //不显示遮罩
                       //     }, function(){
                       //         layer.closeAll();
                       //         window.location.href = "http://47.95.33.183:9090/index"
                       //     });
                       // }
                }));
            });
        },
        
        // bindTagit : function () {
        //     $('#tags').tagsinput({
        //         maxTags: 4,
        //         trimValue: true
        //     });
        // },
        
        bindUpload : function () {
            $('#upload_btn').change(function(){
                $(this).upload(_MTONS.BASE_PATH + '/post/upload?crop=thumbnail_post_size', function(data){
                    if (data.status == 200) {
                        var path = data.path;
                        $("#thumbnail_image").css("background", "url(" + path + ") no-repeat scroll center 0 rgba(0, 0, 0, 0)");
                        $("#thumbnail").val(path);
                    }
                });
            });
        },

        bindValidate: function () {
            $("#submitForm").submit(function () {
                if (typeof tinyMCE == "function") {
                    tinyMCE.triggerSave();
                }
            }).validate({
                ignore: "",
                rules: {
                    title: 'required',
                    channelId: 'required',
                    content: {
                        required: true,
                        check_editor: true
                    }
                },
                messages: {
                    title: '请输入标题',
                    channelId: '请选择主题',
                    content: {
                        required: '内容不能为空',
                        check_editor: '内容不能为空'
                    }
                },
                errorElement: "p",
                errorPlacement: function (error, element) {
                    error.addClass("help-block");
                    if (element.prop("type") === "checkbox") {
                        error.insertAfter(element.parent("label"));
                    } else if (element.is("textarea")) {
                        error.insertAfter(element.closest(".form-group"));
                    } else {
                        error.insertAfter(element);
                    }
                },
                highlight: function (element, errorClass, validClass) {
                    $(element).closest("div").addClass("has-error").removeClass("has-success");
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).closest("div").addClass("has-success").removeClass("has-error");
                }
            });

        }
    };
	
	exports.init = function () {
        // require.async(['validation', 'validation-additional'], function () {
		    new PostView().init();
        // });
	}
});