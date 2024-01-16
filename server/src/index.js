const express = require('express');
const app = express();
const port = process.env.PORT || 3001; // Puedes cambiar el puerto según tus necesidades

app.get('/', (req, res) => {
  res.send('¡Hola desde Express!');
});

app.listen(port, () => {
  console.log(`Servidor Express escuchando en el puerto ${port}`);
});