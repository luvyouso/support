$(function(){
  $(".dial").knob();
  
  for (var a=[],i=0;i<20;++i) a[i]=i;

  // http://stackoverflow.com/questions/962802#962890
  function shuffle(array) {
    var tmp, current, top = array.length;
    if(top) while(--top) {
      current = Math.floor(Math.random() * (top + 1));
      tmp = array[current];
      array[current] = array[top];
      array[top] = tmp;
    }
    return array;
  }
  
	/*
  $(".pbar").peity("bar", {
    colours: ["#EA494A"],
    strokeWidth: 4,
    height: 32,
      max: null,
      min: 0,
      spacing: 4,
      width: 58
  }); */
});

$(document).ready(function() {



});