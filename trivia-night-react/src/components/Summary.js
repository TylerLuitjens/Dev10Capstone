import { useEffect, useState, useContext } from "react";
import '../components/user/Leaderboard.css';
import {Link} from 'react-router-dom';

function Summary( { game } ) {

    // create set state for game users
    const [gameUsers, setGameUsers] = useState([]);

    const fetchSummary = () => {
        fetch(`http://localhost:8080/game/gameusers/${game.gameCode}`)
            .then(response => response.json())
            .then(data => setGameUsers(data))
            .catch(error => console.log(error));
    };

    useEffect(() => {
        console.log(game.gameCode);
        console.log(game);
        if (game !== [] && gameUsers === []) {
            fetchSummary();
        }
    }, [game]);

    return (
        <>
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h1 className="display-4">{game['gameCode']} Summary</h1>
                </div>
            </div>
            <table className="table table-dark">
                <thead className="thead-dark">
                    <tr>
                        <th scope="col">Rank</th>
                        <th scope="col">Username</th>
                        <th scope="col">Attempts</th>
                        <th scope="col">Correct</th>
                    </tr>
                </thead>
                <tbody> 
                    {gameUsers.map(users => (
                        <tr key={users.userId}>
                            <td></td>
                            <td>{users.userName}</td>
                            <td>{users.numAnswered}</td>
                            <td>{users.numCorrect}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="d-flex justify-content-center">
                <Link to={'/'} className="btn btn-lg btn-info mt-4">Return to Home</Link>
            </div>
        </>
    );
}

export default Summary;