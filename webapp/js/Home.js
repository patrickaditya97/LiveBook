console.log("started")

let hiddenBtn = document.getElementById("hiddenUploadBtn")
let uploadBtn = document.getElementById("btnUploadImage")
let imagePreview = document.getElementById('imageUploadPreview')
let file = new FileReader();


window.addEventListener('click', function(){
    if(event.target.id == 'btnUploadImage')
    {
        hiddenBtn.click()
    }
    else if(event.target.id == 'btnPostToWall')
    {
        let formData = new FormData()

        let caption = document.getElementById('cardTextBox').value;
        formData.append('photo', hiddenBtn.value)
        formData.append('caption', caption)

        let xhr = new XMLHttpRequest()

        xhr.onreadystatechange = function()
        {
            if(this.readyState == 4 && this.status==400)
            {
                console.log("done")
            }
        }
        xhr.open("POST", "/postIt")
        xhr.send(formData)

    }
})

hiddenBtn.addEventListener('change', function(){
    document.getElementById('newPostBody').style.height='364px'
    document.getElementsByClassName('previewImage')[0].style.display='block'
    imagePreview.src =  URL.createObjectURL(hiddenBtn.files[0])

    // console.log(hiddenBtn.files[0])

})