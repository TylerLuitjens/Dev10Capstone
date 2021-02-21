import { useContext, useEffect, useState } from "react";
import './Leaderboard.css';
import {Link} from 'react-router-dom';
import AuthContext from '../AuthContext';

function Leaderboard() {
    const [leaderboard, setLeaderboard] = useState([]);

    const auth = useContext(AuthContext);

    const fetchLeaderboard = () => {
        fetch('http://localhost:8080/user/leaderboard', {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${auth.user.token}`
            }
        })

        }

    return (
        <>
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h1 className="display-4">Leaderboard</h1>
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
                    {leaderboard.map(leader => (
                        <tr key={leader.userId}>
                            <td></td>
                            <td>{leader.userName}</td>
                            <td>{leader.numAnswered}</td>
                            <td>{leader.numCorrect}</td>
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

// {rankings.map(rank => (
//     <td>{rank}</td>
// ))}
export default Leaderboard;