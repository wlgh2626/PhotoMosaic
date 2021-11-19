export default function FileChooser({ setFile, setFileUrl }) {
  function selectFile(chosenFiles) {
    if (setFileUrl) {
      setFile(chosenFiles[0]);
      setFileUrl(URL.createObjectURL(chosenFiles[0]));
    } else {
      setFile([...chosenFiles]);
    }
  }

  return (
    <input
      type="file"
      accept="image/*"
      onChange={(event) => {
        selectFile(event.target.files);
      }}
      hidden
    />
  );
}
