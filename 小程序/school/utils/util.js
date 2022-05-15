const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

module.exports = {
  formatTime: formatTime
}
//将时间戳转换为时间,可以精确到秒
// function formatTimeTwo(number, format) {
//   var formateArr = ['Y', 'M', 'D', 'H', 'M', 'S'];
//   var returnArr = [];
//   var date = new Date(number * 1000);
//   returnArr.push(date.getFullYear());
//   returnArr.push(formatNumber(date.getMonth() + 1));
//   returnArr.push(formatNumber(date.getDate()));
//   returnArr.push(formatNumber(date.getHours()));
//   returnArr.push(formatNumber(date.getMinutes()));
//   returnArr.push(formatNumber(date.getSeconds()));
//   for (var i in returnArr) {
//     format = format.replace(formateArr[i], returnArr[i]);
//   }
//   return format;
// }
//将方法暴露出去
// module.exports = {
//     formatTimeTwo: formatTimeTwo
// }