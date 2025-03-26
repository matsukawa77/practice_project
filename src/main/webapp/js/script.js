 
 
 function searchPostalCode() {
        const postalCodeInput = document.getElementById("postalCode");
        const postalCode = postalCodeInput.value.replace(/-/g, ''); // ハイフンを除去

        if (!/^\d{7}$/.test(postalCode)) {
            alert("有効な郵便番号を入力してください。");
            return;
        }

        fetch(`https://zipcloud.ibsnet.co.jp/api/search?zipcode=${postalCode}`)
            .then(response => response.json())
            .then(data => {
                if (data.results && data.results.length > 0) {
                    const address = data.results[0].address1 + data.results[0].address2 + data.results[0].address3;
                    document.getElementById("address").value = address;
                    // ハイフンありの形式で郵便番号をセット
                    const formattedPostalCode = postalCode.slice(0, 3) + "-" + postalCode.slice(3);
                    postalCodeInput.value = formattedPostalCode;
                } else {
                    alert("郵便番号が見つかりませんでした。");
                }
            })
            .catch(error => {
                console.error("郵便番号検索エラー:", error);
                alert("郵便番号検索に失敗しました。");
            });
    }