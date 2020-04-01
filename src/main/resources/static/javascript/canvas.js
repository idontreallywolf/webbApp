/*
    Wait for the web page to load before performing any action.
*/
window.addEventListener("load", function(){
    main();
});

let canvas  = null;
let ctx     = null;
let meme    = null;

let canvasPaddingTop    = 50;   // px
let canvasPaddingBottom = 40;   // px

let topText     = "";
let bottomText  = "";

let minSize     = 10;
let topSize     = 30;
let bottomSize  = topSize; // default

function main(){
    canvas = document.getElementById("canvas");
    ctx = canvas.getContext("2d");

    loadImage();
    textInputListeners();
    sliders();
}

/**
    Loads selected image into canvas.
    & initializes global `meme` variable
*/
function loadImage(){

    document.getElementById('uploadedImage').addEventListener('change', function(data){
        let reader = new FileReader();
        let _uploader = this;
        reader.onload = function(event){

            // reset canvas & text inputs
            clearCanvas();
            clearTextInputs();

            // Validate file type
            if(!isValidImage(_uploader.files[0])){
                alert('ERROR: Not a valid image file.\n(png, jpg, jpeg, gif) only!')
                return false;
            }

            var img = new Image();
            img.onload = function(){

                // minimum image size
                if(img.width <= 300 || img.height <= 300){
                    alert('Image too small. Min 400x400');
                    return false;
                }

                // fit canvas to image size
                canvas.width = img.width;
                canvas.height = img.height;

                // use img as meme
                meme = img;

                // draw image on canvas
                ctx.drawImage(img, 0, 0);
            }

            img.src = event.target.result;
        }

        reader.readAsDataURL(event.target.files[0]);
        return false;
    });
}

/**
    When input event occurs on one of the fields,
    corresponding text is updated and canvas is redrawn.
*/
function textInputListeners(){
    let textFirst = document.getElementById('topText');
    let textSecond = document.getElementById('bottomText');

    textFirst.addEventListener('input', function(input){
        topText = this.value.toUpperCase();
        clearAndDraw();
    });

    textSecond.addEventListener('input', function(input){
        bottomText = this.value.toUpperCase();
        clearAndDraw();
    });
}

/**
    Updates top/bottom text size when change event occurs.
*/
function sliders(){
    let sliderTop = document.getElementById('slider_topTxtSize');
    let sliderBottom = document.getElementById('slider_bottomTxtSize');

    sliderTop.addEventListener('change', function(input){
        topSize = (this.value <= minSize ? minSize:this.value);
        clearAndDraw();
    });

    sliderBottom.addEventListener('change', function(input){
        bottomSize = (this.value <= minSize ? minSize:this.value);
        clearAndDraw();
    });
}

/**
    Resets input fields
*/
function clearTextInputs(){
    document.getElementById('topText').value = "";
    document.getElementById('bottomText').value = "";
    topText = "";
    bottomText = topText;
}

/**
    Validates file types

    @param file - uploaded file object
    @return false, if fileType is not one of ('png','gif','jpg','jpeg').
*/
function isValidImage(file){
    let type = file.type.split("/")[1];
    let validTypes = ['png','gif','jpg','jpeg'];
    if(!validTypes.includes(type)){
        return false;
    }
    return true;
}


function clearCanvas(){
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}

function draw(){
    ctx.drawImage(meme, 0, 0, canvas.width, canvas.height);

    // draw texts
    ctx.strokeStyle = "#000";
    ctx.lineWidth = 5;
    ctx.fillStyle = "#fff";
    ctx.miterLimit = 2;

    console.log(topSize, bottomSize);

    ctx.font = topSize+"px Impact";
    let topText_X = Math.floor(canvas.width/2) - Math.floor(getTextWidth(topText, ctx.font)/2);
    ctx.strokeText(topText, topText_X, canvasPaddingTop);
    ctx.fillText(topText, topText_X, canvasPaddingTop);

    ctx.font = bottomSize+"px Impact";
    let bottomText_X = Math.floor(canvas.width/2) - Math.floor(getTextWidth(bottomText, ctx.font)/2);
    ctx.strokeText(bottomText, bottomText_X, canvas.height-canvasPaddingBottom);
    ctx.fillText(bottomText, bottomText_X, canvas.height-canvasPaddingBottom);
}

function clearAndDraw(){
    clearCanvas();
    draw();
}

function getTextWidth(text, font) {
    ctx.font = font;
    var metrics = ctx.measureText(text);
    return metrics.width;
}
