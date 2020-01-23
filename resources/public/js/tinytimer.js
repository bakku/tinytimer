(function() {
  function zeroPadded(integer) {
    if (integer < 10) {
      return "0" + integer;
    } else {
      return String(integer);
    }
  }

  function exampleTimeDecrementer() {
    const timeEl = document.querySelector('.example-time');

    if (timeEl) {
      const currentTime = timeEl.innerText;

      const seconds = parseInt(currentTime.split(":")[2]);
      const minutes = parseInt(currentTime.split(":")[1]);
      const hours   = parseInt(currentTime.split(":")[0]);

      if (seconds == 0 && minutes == 0 && hours == 0) {
        return;
      }

      if (seconds > 0) {
        timeEl.innerText = zeroPadded(hours) + ":" + zeroPadded(minutes) + ":" + zeroPadded(seconds - 1);
      } else if (minutes > 0) {
        timeEl.innerText = zeroPadded(hours) + ":" + zeroPadded(minutes - 1) + ":59"
      } else {
        timeEl.innerText = zeroPadded(hours - 1) + ":59:59"
      }

      setTimeout(exampleTimeDecrementer, 1000);
    }
  }

  exampleTimeDecrementer();
}());
