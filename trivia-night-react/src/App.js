import Home from "./components/Home";
import Create from "./components/user/Create";
import Login from "./components/Login";
import Leaderboard from "./components/user/Leaderboard";

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from 'react-router-dom';

function NotFound() {
  return(
      <h2>Not Found</h2>
  )
}

function App() {
  return (
    <Router>

      <a>
        <Link to="/">Home</Link>
        <Link to="/login">Login</Link>
        <Link to="/user/create">Create User</Link>
        <Link to="/user/leaderboard">Leaderboard</Link>
      </a>

      <Switch>

        <Route exact path="/"> 
          <Home />
        </Route>

        <Route path="/login">
          <Login />
        </Route>

        <Route path="/user/create">
          <Create />
        </Route>

        <Route path="/user/leaderboard">
          <Leaderboard />
        </Route>

        <Route path="*">
          <NotFound />
        </Route>

      </Switch>

    </Router>
  );
}

export default App;
