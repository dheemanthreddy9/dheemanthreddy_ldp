import { Link, Route, Routes } from 'react-router-dom';

import Home from './pages/Home';
import Students from './pages/Students';
import StudentDetails from './pages/StudentDetails';
import About from './pages/About';

const App = () => {
  return (
    <>
      <nav>
        <Link to="/">Home</Link>
        {' | '}
        <Link to="/students">Students</Link>
        {' | '}
        <Link to="/about">About</Link>
      </nav>

      <hr />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/students" element={<Students />} />
        <Route
          path="/students/:id"
          element={<StudentDetails />}
        />
        <Route path="/about" element={<About />} />
      </Routes>
    </>
  );
};

export default App;