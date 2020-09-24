import {getBearer, LOCALHOST_URL} from "./data.js";

let tags = [];

const fetchAllTags = () => {
    if (getBearer() === null) {
        alert("you need to login to access this function");
    }

    fetch(`${LOCALHOST_URL}/tag`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        },
    }).then((tagList) => {
        tagList.json().then((tagList) => {
            tags = tagList;
            renderTags();
        });
    })
}

const deleteTag = (id) => {
    fetch(`${LOCALHOST_URL}/tag/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        }
    }).then(() => {
        fetchAllTags();
    });
}

const getOneTag = (id) => {
    fetch(`${LOCALHOST_URL}/tag/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        }
    }).then((result) => {
        result.json().then((result) => {
            loadData(result);
        })
    })
}

const createTag = (tag) => {
    fetch(`${LOCALHOST_URL}/tag`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        },
        body: JSON.stringify(tag)
    }).then((res) => {
        if (res.ok) {
            resetForm();
            fetchAllTags();
        } else {
            console.log("There was an error. " + res.status);
        }
    });
}

const editTag = (tag) => {
    fetch(`${LOCALHOST_URL}/tag`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        },
        body: JSON.stringify(tag)
    }).then((res) => {
        if (res.ok) {
            resetForm();
            fetchAllTags();
        } else {
            console.log("There was an error. " + res.status);
        }
    });
}

const renderTags = () => {
    const display = document.querySelector('#tagDisplay');
    display.innerHTML = '';

    tags.forEach((tag) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(tag.id));
        row.appendChild(createCell(tag.name));

        let button = document.createElement('input');
        button.type = 'button';
        button.value = 'Delete';

        button.onclick = (e) =>  {
            e.stopPropagation();
            deleteTag(tag.id);
        }

        row.appendChild(button);

        row.onclick = () => getOneTag(tag.id);

        display.appendChild(row);
    });
};

const submitOnClick = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const tag = {};

    tag['name'] = formData.get('name');
    let id = formData.get('id');

    console.log(tag);

    if (id === '' || id === null) {
        createTag(tag)
    } else {
        tag['id'] = id;
        editTag(tag);
    }
}

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const loadData = (tag) => {
    document.getElementById("name").value = tag.name;
    document.getElementById("tag-id").value = tag.id;
    document.getElementById("submit-btn").value = "Update";
    document.getElementById("cancel-btn").style.display = "block";
}

const resetForm = () => {
    document.getElementById("name").value = "";
    document.getElementById("tag-id").value = "";
    document.getElementById("submit-btn").value = "Send";
    document.getElementById("cancel-btn").style.display = "none";
}

window.addEventListener('load', () => fetchAllTags());

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('#tag-form');
    loginForm.addEventListener('submit', submitOnClick);
})

document.getElementById('cancel-btn').addEventListener('click', resetForm);