import { Link } from 'react-router-dom';

const Students = () => {
  return (
    <div>
      <h1>Students Page</h1>

      <ul>
        <li>
          <Link to="/students/1">Dheemanth</Link>
        </li>

        <li>
          <Link to="/students/2">Nikhil</Link>
        </li>

        <li>
          <Link to="/students/3">Rahul</Link>
        </li>
      </ul>
    </div>
  );
};

export default Students;