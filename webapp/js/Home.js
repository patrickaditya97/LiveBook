console.log("started")

function backEndCall(callback, url, data) {
    let xhr = new XMLHttpRequest()

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            callback()
        }
    }
    xhr.open("POST", url)
    xhr.send(data)

}



//Post Creation
let hiddenBtn = document.getElementById("hiddenUploadBtn")
let imagePreview = document.getElementById('imageUploadPreview')
let caption = document.getElementById('cardTextBox');


window.addEventListener('click', function () {
    if (event.target.id == 'btnUploadImage') {
        hiddenBtn.click()
    }
    else if (event.target.id == 'btnPostToWall') {

        if (!caption.value && !hiddenBtn.value) {
            return
        }

        let formData = new FormData()

        formData.append('photo', hiddenBtn.value);

        // let xhr = new XMLHttpRequest()

        // xhr.onreadystatechange = function()
        // {
        //     if(this.readyState == 4 && this.status==200)
        //     {
        //         clearForm()
        //     }
        // }
        // xhr.open("POST", "/postIt?caption=" + caption)
        // xhr.send(formData)

        backEndCall(clearForm, "/postIt?caption=" + caption.value, formData)

    }
})

function clearForm() {
    caption.value = ''
    hiddenBtn.value = ''
    document.getElementById('newPostBody').style.removeProperty("height");
    document.getElementsByClassName('previewImage')[0].style.display = "none";
}

hiddenBtn.addEventListener('change', function () {
    document.getElementById('newPostBody').style.height = '364px'
    document.getElementsByClassName('previewImage')[0].style.display = 'block'
    imagePreview.src = URL.createObjectURL(hiddenBtn.files[0])
})




//Post Retrieval
