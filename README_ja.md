# JATiCA
JATiCA(**J**ust **A** **Ti**nkers' **C**onstruct/**C**ompatibility **A**ttempt)は、JAOPCAに登録されている素材をTinkers' Constructの素材として追加するModです。  
第三者が開発したアドオンModなので、このModに関する質問や不具合報告はJAOPCAまたはTinkers' Constructの開発者ではなく、私にお願いします。

## セットアップ
1. JAOPCA、Tinkers' Construct、JATiCAを導入します。
2. Minecraftを起動します。
3. `config/jaopca/modules/jatica_materials.toml`を開き、`passiveMaterialWhitelist = []`の中にTinkers' Constructの素材として追加したいJAOPCA素材の名前を追加します。  
    `["*"]`とすることで、JAOPCAに登録されている全ての素材をTinkers' Constructの素材として追加できます。
4. Minecraftを再起動します。
5. `config/jaopca/materials/`の中の`<素材名>.toml`を開き、`[jatica]`セクションで素材のプロパティを設定します。  
    デフォルトではどのパーツも生成されないようになっているので、必要なパーツを`true`に設定してください。
6. Minecraftを再起動すると、Tinkers' Constructの素材が追加されています。

## 素材名の変更
Tinkers' Construct内で表示される素材名を変更したい場合、`material.jatica.<素材名>`というキーを追加してください。
> <素材名>の小型刃、<素材名>のマトック、等
```json
{
    "material.jatica.amethyst": "Cool Amethyst"
}
```
JAOPCA内で表示される素材名を変更したい場合、`jaopca.material.<素材名>`というキーを追加してください。
> 溶融<素材名>、<素材名>のブロック、等
```json
{
    "jaopca.material.amethyst": "Cool Amethyst"
}
```

## 謝辞
JAOPCAおよびTinkers' Constructの開発者に感謝します。
- [JAOPCA](https://github.com/TheLMiffy1111/JAOPCA)
- [Tinkers' Construct](https://github.com/SlimeKnights/TinkersConstruct)
