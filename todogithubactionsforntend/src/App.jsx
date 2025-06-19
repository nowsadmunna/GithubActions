import { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState('');

  const fetchTodos = async () => {
    const res = await axios.get('http://localhost:8091/api/todos');
    setTodos(res.data);
  };

  useEffect(() => {
    fetchTodos();
  }, []);

  const addTodo = async () => {
    if (!title.trim()) return;
    await axios.post('http://localhost:8091/api/todos', { title, completed: false });
    setTitle('');
    fetchTodos();
  };

  const toggleComplete = async (todo) => {
    await axios.put(`http://localhost:8091/api/todos/${todo.id}`, {
      ...todo,
      completed: !todo.completed,
    });
    fetchTodos();
  };

  const deleteTodo = async (id) => {
    await axios.delete(`http://localhost:8091/api/todos/${id}`);
    fetchTodos();
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-5 border rounded shadow">
      <h1 className="text-2xl font-bold mb-5 text-center">To-Do List</h1>

      <div className="flex mb-5">
        <input
          type="text"
          placeholder="Add a new task"
          value={title}
          onChange={e => setTitle(e.target.value)}
          className="flex-grow border rounded px-3 py-2"
        />
        <button onClick={addTodo} className="ml-3 bg-blue-600 text-white px-4 rounded hover:bg-blue-700">
          Add
        </button>
      </div>

      <ul>
        {todos.map(todo => (
          <li key={todo.id} className="flex items-center justify-between mb-3">
            <label className={`flex items-center cursor-pointer ${todo.completed ? 'line-through text-gray-500' : ''}`}>
              <input
                type="checkbox"
                checked={todo.completed}
                onChange={() => toggleComplete(todo)}
                className="mr-3"
              />
              {todo.title}
            </label>
            <button
              onClick={() => deleteTodo(todo.id)}
              className="text-red-500 hover:text-red-700"
              aria-label="Delete todo"
            >
              &times;
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
