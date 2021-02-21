import { useState, useEffect } from "react";
import { Link, useHistory, useLocation } from 'react-router-dom';
import Errors from './Errors';


function Game({game, user}) {
    const [errors, setErrors] = useState([]);
    const [currentUser, setCurrentUser] = useState([]);
    const history = useHistory();

    var index = 0;
    // if ( currentUser === [] && user !== null) {
    //     game['gameUsers'].forEach(element => {
    //         if (element['userId'] === user['userId']) {
    //             setCurrentUser(element);
    //         }
    //     });
    // }

    // TODO delete
    let tempUser = [];
    tempUser['userId'] = 10;
    tempUser['numAnswered'] = 0;
    tempUser['numCorrect'] = 0;
    console.log(tempUser);
    // setCurrentUser(tempUser);

    // Current question is going to be based off of the current number of questions answered by the user
    index = tempUser['numAnswered'];
    console.log(index);

    const handleSubmit = (event) => {
        event.preventDefault();
        // TODO update gameUser after completing all of the questions
    }

    const handleSelection = (event) => {

    }

    let question = game['gameQuestions'][index]['question'];


    return(

        <>
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h4>{game['gameCode']}</h4>
                </div>
                <div className="d-flex justify-content-center center">
                    <h1 className="display-4">{question}</h1>
                </div>
            </div>
            <Errors errors={errors} />

            <div className="d-flex justify-content-center mt-5">
                <button className="button btn-success btn-lg btn-block col-md-8" onClick={() => index++}>Hello</button>
            </div>
        </>
    )
}

export default Game;