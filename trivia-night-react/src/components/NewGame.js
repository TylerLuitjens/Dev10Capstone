import { useState, useEffect, useContext } from "react";
import { Link, useHistory, useLocation } from 'react-router-dom';
import Errors from './Errors';
import AuthContext from './AuthContext';


function NewGame({setGame, game} ) {
    const auth = useContext(AuthContext);
    const [category, setCategory] = useState('');
    const [errors, setErrors] = useState([]);
    const history = useHistory();
    const [gameCode, setGameCode] = useState("");

    const handleSubmit = async (event) => {

        const userJson = JSON.stringify(auth.user); // TODO change to user
        event.preventDefault();
        
        let url = `${process.env.REACT_APP_API_URL}/game/` + event.target.value;

        const init = {
            method: "POST",
            headers: {
                "Content-Type" : "application/json",
                "Accept": "application/json",
                "Authorization": `Bearer ${auth.user.token}`
            },
            body: userJson
        }

        fetch(url, init)
            .then(response => response.json())
            .then((data) => {
                setGame(data);
                setGameCode(data['gameCode']);
            })
            .then(history.push('/Game/' + gameCode))
            .catch(error => console.log(error));
    }


    useEffect( () => {
        if (game !== [] && gameCode != "") {
            history.push("/Game")
        }
    }, [game]);

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