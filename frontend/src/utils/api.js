import axios from 'axios';

export default axios.create({
  // baseURL: 'http://k3a206.p.ssafy.io:8888/chuck/',
  baseURL: 'http://localhost:8888/chcuk/',
  headers: {
    'Content-Type': 'application/json',
  },
});