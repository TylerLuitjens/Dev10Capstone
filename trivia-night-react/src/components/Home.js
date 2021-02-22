import triviaNightLogo from '../images/TriviaNightLogoTransparentScaled.png';
import AuthContext from './AuthContext';
import { Link } from 'react-router-dom';
import { useContext } from 'react';

function Home({ Alert }) {
    const auth = useContext(AuthContext);
    return (
        <>
            <div className="d-flex justify-content-center mb-5">
                {!auth.user && (
                    <>
                        <Link to={'/login'} className="btn btn-lg btn-primary">Log In</Link>
                        <Link to={'/user/create'} className="btn btn-lg ml-5 btn-warning">Create User</Link>
                    </>
                )}
                {auth.user && (
                    <>
                    <Link to={'/'} className="btn btn-lg btn-primary" onClick={auth.logout}>Log Out</Link>
                    </>
                )}
                <Link to={'/user/leaderboard'} className="btn btn-lg ml-5 btn-info">Leaderboard</Link>
                {auth.user ? (
                    <>
                        <Link to={'/newgame'} className="btn btn-lg ml-5 btn-secondary">New Game</Link>
                        <Link to={'/joingame'} className="btn btn-lg ml-5 btn-success">Join Game</Link>
                    </>
                ) : (
                        <>
                            <Link to={'/newgame'} className="btn btn-lg ml-5 btn-secondary" onClick={Alert}>New Game</Link>
                            <Link to={'/joingame'} className="btn btn-lg ml-5 btn-success" onClick={Alert}>Join Game</Link>
                        </>
                    )}
            </div>

            <div className="d-flex justify-content-center">
                <img src={triviaNightLogo} alt="Trivia Night Logo" />
            </div>
        </>
    )
}

export default Home;