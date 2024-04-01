export function copyToClip(text: string) {
  return navigator.clipboard
    .writeText(text)
    .then(() => {
      return Promise.resolve(text);
    })
    .catch((error) => {
      return Promise.reject(error);
    });
}
