const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    deleteButton.addEventListener('click', event=>{
        let id = document.getElementById('post-id').value;
        fetch(`/api/posts/${id}`, {
            method: 'DELETE'
        })
            .then(()=>{
                alert('삭제가 완료되었습니다.');
                location.replace('/posts');
            });
    });
}

const modifyButton = document.getElementById('modify-btn');

if(modifyButton){
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/posts/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(()=>{
                alert('수정이 완료되었습니다.');
                location.replace(`/posts/${id}`);
            });
    });
}

const createButton = document.getElementById('create-btn');
if(createButton){
    createButton.addEventListener("click", (event)=>{
        fetch("/api/posts", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        })
            .then(()=>{
                alert("등록이 완료되었습니다.");
                location.replace("/posts");
            });
    });
}