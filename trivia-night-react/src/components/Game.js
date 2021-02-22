import { useState } from "react";
import { Link, useHistory, useLocation, useParams } from 'react-router-dom';
import Errors from './Errors';


function Game({game, user, setGame}) {

    // TODO delete and refactor this to use the current logged in user once that is all up and running
    let tempUser = [];
    tempUser['userId'] = 10;
    tempUser['numAnswered'] = 0;
    tempUser['numCorrect'] = 0;

    const [errors, setErrors] = useState([]);
    const [currentUser, setCurrentUser] = useState([]);
    const history = useHistory();
    const [activeIndex, setActiveIndex] = useState(tempUser['numAnswered']); // FIXME change this to currently logged in user instead
    let  { gameCode } = useParams();
    
    // if ( currentUser === [] && user !== null) {
    //     game['gameUsers'].forEach(element => {
    //         if (element['userId'] === user['userId']) {
    //             setCurrentUser(element);
    //         }
    //     });
    // }

    // Current question is going to be based off of the current number of questions answered by the user
   
    let question = [];
    question['question'] = "Loading...";

    if (game['gameQuestions'] !== undefined) {
        question = game['gameQuestions'][activeIndex];
    }
    const handleSubmit = () => {
        // TODO update gameUser after completing all of the questions
        setGame([]);
        history.push("/user/leaderboard"); // FIXME this will need to go to the summary page instead
    }

    const handleSelection = (event) => {
        setActiveIndex(activeIndex + 1);
        if (activeIndex >= 10) {
            handleSubmit();
        }
    }

    if (game !== [] && game['gameQuestions'] !== undefined && game['gameQuestions'] !== null) {
        return(
            <>
                <div className="jumbotron mb-3">
                    <div className="d-flex justify-content-center">
                        <h4>{game['gameCode']}</h4>
                    </div>
                    <div className="d-flex justify-content-center center">
                        <h1 className="display-4">{question['question']}</h1>
                    </div>
                </div>
                <Errors errors={errors} />
    
                <div className="d-flex justify-content-center mt-5">
                    <button className="button btn-info btn-lg btn-block col-md-8">{question['answers'][0]['answer']}</button>
                </div>
                <div className="d-flex justify-content-center mt-5">
                    <button className="button btn-info btn-lg btn-block col-md-8">{question['answers'][1]['answer']}</button>
                </div>
                <div className="d-flex justify-content-center mt-5">
                    <button className="button btn-info btn-lg btn-block col-md-8">{question['answers'][2]['answer']}</button>
                </div>
                <div className="d-flex justify-content-center mt-5">
                    <button className="button btn-info btn-lg btn-block col-md-8">{question['answers'][3]['answer']}</button>
                </div>
    
                <div className="d-flex justify-content-center mt-5">
                    <button className="button btn-warning btn-lg btn-block col-md-4" onClick={ () => handleSelection()}>Next</button>
                </div>
            </>
        )
    } else {
        return (
            <>
            <div className="jumbotron mb-3">
                    <div className="d-flex justify-content-center center">
                        <h1 className="display-4">Loading...</h1>
                    </div>
                </div>
            </>
        )
    }

    
}

export default Game;