import { useState, useEffect } from "react";
import { Link, useHistory, useLocation } from 'react-router-dom';
import Errors from './Errors';


function NewGame({setGame, game, user}) {
    const [category, setCategory] = useState('');
    const [errors, setErrors] = useState([]);
    const history = useHistory();
    const [gameCode, setGameCode] = useState("");

    const handleSubmit = async (event) => {
        // TODO delete this
        const newUser = {...user};
        newUser["userId"] = 1;
        newUser["userName"] = "Temp User Name";
        newUser["password"] = "SuperSecure123";
        newUser["numAnswered"] = 0;
        newUser["numCorrect"] = 0;

        const userJson = JSON.stringify(newUser); // TODO change to user
        event.preventDefault();
        let url = "http://localhost:8080/game/" + event.target.value;

        const init = {
            method: "POST",
            headers: {
                "Content-Type" : "application/json",
                "Accept": "application/json"
            },
            body: userJson
        }

        fetch(url, init)
            .then(response => response.json())
            .then((data) => {
                setGame(data);
                setGameCode(data['gameCode']);
            })
            .then(history.push('/Game'))
            .catch(error => console.log(error));
    }



    return(
        <>
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h1 className="display-4">New Game</h1>
                </div>
            </div>

            <Errors errors={errors} />

            <div className="d-flex justify-content-center mt-4">
                    <button className="button btn-success btn-lg btn-block col-md-8" onClick={handleSubmit} value="Celebrities">Celebrities</button>              
            </div>
            <div className="d-flex justify-content-center mt-4">
                    <button className="button btn-success btn-lg btn-block col-md-8" onClick={handleSubmit} value="Science: Computers">Computer Science</button>              
            </div>
            <div className="d-flex justify-content-center mt-4">
                    <button className="button btn-success btn-lg btn-block col-md-8" onClick={handleSubmit} value="General Knowledge">General Knowledge</button>              
            </div>
            <div className="d-flex justify-content-center mt-4">
                    <button className="button btn-success btn-lg btn-block col-md-8" onClick={handleSubmit} value="History">History</button>              
            </div>
            <div className="d-flex justify-content-center mt-4">
                    <button className="button btn-success btn-lg btn-block col-md-8" onClick={handleSubmit} value="Mythology">Mythology</button>              
            </div>
            <div className="d-flex justify-content-center mt-4">
                    <button className="button btn-success btn-lg btn-block col-md-8" onClick={handleSubmit} value="Sports">Sports</button>              
            </div>


            <div className="d-flex justify-content-center mt-2">
                    <Link to={'/'} className="btn btn-lg btn-info mt-4">Return to Home</Link>
            </div>
        </>
    )
}

export default NewGame;