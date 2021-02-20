import triviaNightLogo from '../images/TriviaNightLogoTransparentScaled.png';
import {Link} from 'react-router-dom';
function Home() {
    return(
        <>
            <div className="d-flex justify-content-center mb-5">
                <Link to={'/login'} className="btn btn-lg btn-primary">Log In</Link>
                <Link to={'/user/create'} className="btn btn-lg mx-5 btn-warning">Create User</Link>
                <Link to={'/user/leaderboard'} className="btn btn-lg btn-info">Leaderboard</Link>
                <Link className="btn btn-lg ml-5 btn-success">New Game</Link>
            </div>

            <div className="d-flex justify-content-center">
                <img src={triviaNightLogo} alt="Trivia Night Logo" />
            </div>    
        </>
    )
}

export default Home;