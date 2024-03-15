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