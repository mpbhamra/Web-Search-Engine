let 	express = require('express'),
		path = require('path');
        app = express();
// Point static path to dist
app.use(express.static(path.join(__dirname, 'web-pages')));

// Catch all other routes and return the index file
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'web-pages/index.html'));
});

module.exports = app;
