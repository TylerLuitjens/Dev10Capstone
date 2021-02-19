import {useState} from 'react';
import Errors from '../Errors';
import {
    BrowserRouter as Router,
    useHistory,
    Link
  } from 'react-router-dom';

function Create() {
    const [userName, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState([]);

    const history = useHistory();

    // const handleCreateUser = (event) => {

    //     const init = {
    //         method: 'POST',
    //         headers: {
    //             "Content-Type": "application/json"
    //         },
    //         body: JSON.stringify({
    //             userName,
    //             password
    //         })
    //     }

    //     fetch('http://localhost:8080/user/create', init)
    //         .then(response => {
    //             if (response.status === 201) {
    //                 history.push('/');
    //             } else if (response.status === 400) {
    //                 return response.json();
    //             } else {
    //                 return Promise.reject(`Bad status: ${response.status}. Success requires '201 Created.'`)
    //             }
    //         })
    //         .then (data => {
    //             if (!data.userId) {
    //                 setErrors(data);
    //             }
    //         })
    //         .catch(error => console.log(error))
    // }


    const handleCreateUser = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/user/create', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    userName,
                    password
                })
            })
            console.log(response.status);

            if (response.status === 201) {
                try {
                    history.push('/')
                } catch (error) {
                    throw new Error('Something went wrong');
                }
            }

            if (response.status === 400) {
                
                if (userName === "") {
                    throw new Error('Error: username is required.');
                }
                if (password === "") {
                    throw new Error('Error: password is required.');
                }
                throw new Error('Error: there is something wrong with your username and password.');
            }
        } catch (err) {
            setErrors([err.message]);
        }

    }



    return(
        <>
        <h2>Create User</h2>
            <Errors errors={errors} />
            <form onSubmit={handleCreateUser}>
                <div>
                    <label htmlFor="userName">Username: </label>
                    <input type="text" id="userName" name="userName" placeholder="Enter your username" onChange={(event) => setUsername(event.target.value)}/>
                </div>

                <div>
                    <label htmlFor="pwd" >Password: </label>
                    <input type="password" id="pwd" name="pwd" placeholder="Enter your password" onChange={(event) => setPassword(event.target.value)} />
                </div>
                <div>
                    <button type="submit">Create User</button>
                    <Link to={'/login'}>Login</Link>
                </div>
            </form>
        </>
        
    )
}

export default Create;