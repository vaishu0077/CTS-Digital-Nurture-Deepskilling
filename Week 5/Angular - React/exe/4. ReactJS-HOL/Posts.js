import React from "react";
import Post from "./Post";

class Posts extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            posts: []
        };
    }

    loadPosts() {

        fetch("https://jsonplaceholder.typicode.com/posts")
            .then(response => response.json())
            .then(data => {

                const posts = data.map(item =>
                    new Post(item.id, item.title, item.body)
                );

                this.setState({
                    posts: posts
                });

            });

    }

    componentDidMount() {
        this.loadPosts();
    }

    render() {
        return (
            <div>
                {this.state.posts.map(post => (
                    <div key={post.id}>
                        <h2>{post.title}</h2>
                        <p>{post.body}</p>
                        <hr />
                    </div>
                ))}
            </div>
        );
    }

    componentDidCatch(error, info) {
        alert(error);
    }

}

export default Posts;
