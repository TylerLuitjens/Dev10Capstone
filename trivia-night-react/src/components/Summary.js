import { useEffect, useState, useContext } from "react";
import '../components/user/Leaderboard.css';
import {Link} from 'react-router-dom';

function Summary( {game} ) {

    return (
        <>
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h1 className="display-4">{game['gameCode']} Leaderboard</h1>
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
                    {game['gameUsers'].map(u => (
                        <tr key={u.userId}>
                            <td></td>
                            <td>{u.userName}</td>
                            <td>{u.numAnswered}</td>
                            <td>{u.numCorrect}</td>
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