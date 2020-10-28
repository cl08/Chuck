import axios from 'axios';

export default axios.create({
  baseURL: 'http://k3a206.p.ssafy.io:8888/',
  headers: {
    'Content-Type': 'application/json',
  },
});