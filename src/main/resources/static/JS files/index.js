let videoSource = new Array();
videoSource[0] = "videos/video1.mp4";
videoSource[1] = "videos/video2.mp4";
videoSource[2] = "videos/video3.mp4";
videoSource[3] = "videos/video4.mp4";
let i = 0;
let videoCount = videoSource.length;
let vidElement = document.getElementById('myVideo');




function videoPlay(number){
    vidElement.src = videoSource[number];
    vidElement.autoplay = true;
    vidElement.load();
}

vidElement.addEventListener('ended',myHandler,false);
videoPlay(0);


function myHandler() {
    i++;
    if(i == videoCount){
        i = 0;
        videoPlay(i);
    }
    else{
        videoPlay(i);
    }
}



 function ensureVideoPlays() {
    const video = document.getElementById('myVideo');
 
    if(!video) return;
    
    const promise = video.play();
    if(promise !== undefined){
        promise.then(() => {
            // Autoplay started
        }).catch(error => {
            // Autoplay was prevented.
            video.muted = true;
            video.play();
        });
    }
}
