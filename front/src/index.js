import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { StoreProvider } from '../src/Context/Context.jsx';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <StoreProvider>
      <App />
    </StoreProvider>
  </React.StrictMode>
);
